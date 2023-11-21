import React, { useState } from 'react'
import { Box } from '@chakra-ui/react'
import { Heading, Button } from '@chakra-ui/react'
import { Input } from '@chakra-ui/react'
import { Select } from '@chakra-ui/react'
import { Spinner } from '@chakra-ui/react'
import {
  Slider,
  SliderTrack,
  SliderFilledTrack,
  SliderThumb,
} from '@chakra-ui/react'
import axios from 'axios'
import { FormControl, FormLabel } from '@chakra-ui/react'

const AddPokemonScreen = () => {
  const [title, setTitle] = useState('')
  const [type, setType] = useState('Good')
  const [power, setPower] = useState(30)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const addPokemonHandler = () => {
    setLoading(true)
    if (!title || !type) {
      setLoading(false)
      setError('Please fill all fields')
      return
    }
    var config = {
      method: 'post',
      url: 'http://localhost:9000/api/pockemons',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        'Content-Type': 'application/json',
      },
      data: JSON.stringify({ name: title, type }),
    }

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data))
        setLoading(false)
        setError(null)
        setTitle('')
        setType('')
        setPower(null)
      })
      .catch(function (error) {
        setError(error.response ? error.response.data.error : 'Error Happened')
        setLoading(false)
      })
  }

  return (
    <Box
      p='5'
      mt='4'
      borderWidth='1px'
      borderRadius='lg'
      borderColor='gray.300'
    >
      <Heading>Add Pokemon</Heading>

      <Box m='25px'>
        <FormControl isRequired>
          <FormLabel>Title</FormLabel>
          <Input
            type='text'
            placeholder='Pokemon Name'
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </FormControl>
      </Box>
      <Box m='25px'>
        <FormControl>
          <FormLabel>Pokemon Type</FormLabel>
          <Select
            placeholder='Type'
            onChange={(e) => setType(e.target.value)}
            value={type}
          >
            <option value='Good'>Good</option>
            <option value='Bad'>Bad</option>
          </Select>
        </FormControl>
      </Box>
      <Box m='25px'>
        <FormControl>
          <FormLabel>Pokemon Power</FormLabel>
          <Slider
            aria-label='slider-ex-1'
            defaultValue={30}
            // onChange={(e) => setPower(e.target.value)}
          >
            <SliderTrack>
              <SliderFilledTrack />
            </SliderTrack>
            <SliderThumb />
          </Slider>
        </FormControl>
      </Box>
      <div
        style={{
          color: 'red',
          fontWeight: '600',
          textAlign: 'center',
          margin: '10px',
        }}
      >
        {error}
      </div>
      <Button colorScheme='teal' ml='25px' onClick={addPokemonHandler}>
        {loading ? <Spinner /> : ' Add Pokemon'}
      </Button>
    </Box>
  )
}

export default AddPokemonScreen
