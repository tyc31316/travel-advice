import React from 'react'
import {Button, HStack, Input} from "@chakra-ui/react";

interface SearchDestinationProp {
    onSearch: (destination: String) => void
}

export const SearchDestination: React.FC<SearchDestinationProp> = ({onSearch}) => {

    const [destination, setDestination] = React.useState<String | undefined>()

    const Go = () => {
        if(destination) {
            onSearch(destination)
        }
    }

    return (
        <HStack gap={"1rem"} width={"30rem"}>
            <Input placeholder="Enter your next destination!" onChange={(e) => {setDestination(e.target.value)}}/>
            <Button onClick={Go}>出花!</Button>
        </HStack>
    )
}