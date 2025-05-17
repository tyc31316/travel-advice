
interface SuggestionProps {
    suggestionRawData: String
}

export const Suggestion: React.FC<SuggestionProps> = ({suggestionRawData}) => {

    return (<p> {suggestionRawData} </p>)
}