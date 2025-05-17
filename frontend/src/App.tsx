import React, {use} from 'react'
import { SearchDestination } from './components/search-destination/search-destination'
import { useDestination } from './components/current-destination/use-current-destination'
import { Suggestion } from './components/suggestion/suggestion'

function App() {

    const { destination, onDestinationChange } = useDestination()
    const [ suggestion, setSuggestion ] = React.useState<String | undefined>()

    React.useEffect(() => {

        const getSuggestion = async () => {
            return fetch(`http://localhost:8080/travel/suggestion/${destination}`)
        }

        if(destination){
           getSuggestion().then(response => {
               console.log(`getting suggestion: ${response}`)
               response.text().then(result => {
                   setSuggestion(`${destination}: ${result}`)
               })

           })
        }

    }, [destination])

    return (
        <>
            <SearchDestination onSearch={onDestinationChange}/>
            <Suggestion suggestionRawData={suggestion} />
        </>
    )
}

export default App
