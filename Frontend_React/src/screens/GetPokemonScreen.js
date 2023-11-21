import React, { useState, useEffect } from 'react'
import { Box, Flex, Heading } from '@chakra-ui/react'
import Pokemon from '../Components/Pokemon'
import axios from 'axios'
import { Skeleton, SkeletonCircle, SkeletonText } from '@chakra-ui/react'

const GetPokemonScreen = () => {
  const [pokemons, setPokemons] = useState([])
  const [loading, setLoading] = useState(true)
  const deletePokemon = (id) => {
    console.log(pokemons.filter((pokemon) => pokemon.id != id))
    setPokemons(pokemons.filter((pokemon) => pokemon.id != id))
  }

  useEffect(() => {
    var config = {
      method: 'get',
      url: 'http://localhost:9000/api/pockemons',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    }

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data))
        setPokemons(response.data)
        setLoading(false)
      })
      .catch(function (error) {
        console.log(error)
        setLoading(false)
      })
  }, [])

  return (
    <Box>
      {loading ? (
        <Box>
          <Skeleton height='20px' />
          <Skeleton height='20px' />
          <Skeleton height='20px' />
        </Box>
      ) : (
        <>
          {' '}
          <Heading>All Pokemons</Heading>
          <Flex alignItems='center' gap='2' wrap='wrap'>
            {pokemons.map((pokemon) => (
              <Pokemon pokemon={pokemon} deletePokemon={deletePokemon} />
            ))}
          </Flex>
        </>
      )}
    </Box>
  )
}

export default GetPokemonScreen
