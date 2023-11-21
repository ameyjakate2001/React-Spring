import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import {
  useDisclosure,
  Heading,
  Button,
  Text,
  ButtonGroup,
  Textarea,
} from '@chakra-ui/react'
import { Select } from '@chakra-ui/react'
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
} from '@chakra-ui/react'
import axios from 'axios'
import { Box } from '@chakra-ui/react'
import { FormControl, FormLabel, Input } from '@chakra-ui/react'
import StarRating from './StarRating'
import { AiFillDelete } from 'react-icons/ai'
const Pokemon = ({ pokemon, deletePokemon }) => {
  const { onOpen, isOpen, onClose } = useDisclosure()
  const [description, setDescription] = useState(null)
  const [rating, setRating] = useState(0)
  const initialRef = React.useRef(null)
  const finalRef = React.useRef(null)

  const deletePokemonHandler = () => {
    var config = {
      method: 'delete',
      url: `http://localhost:9000/api/pockemons/${pokemon.id}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    }

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data))
        deletePokemon(pokemon.id)
      })
      .catch(function (error) {
        console.log(error)
      })
  }
  const addReviewHandler = () => {
    console.log(description, rating)
    var config = {
      method: 'post',
      url: `http://localhost:9000/api/reviews/${pokemon.id}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        'Content-Type': 'application/json',
      },
      data: JSON.stringify({
        description,
        stars: rating,
      }),
    }
    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data))
        setDescription('')
        setRating(0)
        onClose()
      })
      .catch(function (error) {
        console.log(error)
      })
  }
  return (
    <Box
      flexGrow='1'
      p='3'
      m='3'
      borderWidth='1px'
      borderRadius='lg'
      borderColor='gray.300'
      position='relative'
    >
      <Heading mb='3' as='h6'>
        {pokemon.name}
      </Heading>
      <Text>{pokemon.type}</Text>
      <Text>50% Power</Text>
      <AiFillDelete
        style={{
          position: 'absolute',
          right: '20px',
          top: '20px',
          fontSize: '1.2em',
          color: 'red',
        }}
        onClick={deletePokemonHandler}
      />
      <ButtonGroup gap='2'>
        <Button colorScheme='teal' size='sm' onClick={onOpen} mt='2'>
          Add Review
        </Button>
        <>
          <Modal
            initialFocusRef={initialRef}
            finalFocusRef={finalRef}
            isOpen={isOpen}
            onClose={onClose}
          >
            <ModalOverlay />
            <ModalContent>
              <ModalHeader>Add Review</ModalHeader>
              <ModalCloseButton />
              <ModalBody pb={6}>
                <FormControl>
                  <FormLabel>Description</FormLabel>
                  <Textarea
                    onChange={(e) => setDescription(e.target.value)}
                    placeholder='Description'
                  />
                </FormControl>

                <FormControl mt={4}>
                  <FormLabel>Stars</FormLabel>
                  <StarRating rating={rating} setRating={setRating} />
                </FormControl>
              </ModalBody>

              <ModalFooter>
                <Button colorScheme='blue' mr={3} onClick={addReviewHandler}>
                  Save
                </Button>
                <Button onClick={onClose}>Cancel</Button>
              </ModalFooter>
            </ModalContent>
          </Modal>
        </>
        <Button colorScheme='teal' size='sm' mt='2'>
          <Link to={`/pokemons/reviews/${pokemon.id}`}>See Reviews</Link>
        </Button>
      </ButtonGroup>
    </Box>
  )
}

export default Pokemon
