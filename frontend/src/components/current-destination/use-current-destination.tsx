import React from 'react'

export const useDestination = () => {
    const [destination, setDestination] = React.useState<String>("")

    const onDestinationChange = (destination: String) => {

        setDestination(destination)
    }

    return { destination, onDestinationChange }
}