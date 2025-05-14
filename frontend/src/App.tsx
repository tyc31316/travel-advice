import React from 'react'
import { Button, HStack, Input } from "@chakra-ui/react"
import { useColorMode} from "@/components/ui/color-mode.tsx";



function App() {
    const { toggleColorMode } = useColorMode()
    return (
        <HStack>
            <Input placeholder="Enter your next destination!"/>
            <Button onClick={toggleColorMode}>Click me</Button>
        </HStack>
    )
}

export default App
