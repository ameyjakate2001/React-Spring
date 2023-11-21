import React from 'react'
import { Box } from '@chakra-ui/react'
import { useNavigate } from 'react-router-dom'
import {
  Flex,
  Spacer,
  Text,
  Heading,
  ButtonGroup,
  Button,
} from '@chakra-ui/react'
import { useContext } from 'react'
import { MdCatchingPokemon } from 'react-icons/md'
import { Link } from 'react-router-dom'
import { UserContext } from '../context/UserContext'
import axios from 'axios'

const Header = () => {
  const navigate = useNavigate()
  const logoutHandler = () => {
    localStorage.removeItem('jwt')
    setUserData(null)
    navigate('/signin')
  }
  const { userData, setUserData } = useContext(UserContext)
  console.log(userData)

  return (
    <Flex
      bg='gray.800'
      p='4'
      minWidth='max-content'
      alignItems='center'
      gap='2'
      color='white'
      as='nav'
    >
      <Box p='2'>
        <Heading as='h3'>
          <Link to='/'>
            pock
            {/* <MdCatchingPokemon /> */}
            <span style={{ color: 'red' }}>Pika</span>
          </Link>
        </Heading>
      </Box>
      <Spacer />
      {userData ? (
        <>
          <Flex gap='4' color='white'>
            <Box p='1' className='links'>
              <Link to='/add'>Add Pokemon</Link>
            </Box>
            <Box p='1' className='links'>
              <Link to='/pokemons'>See Pokemon</Link>
            </Box>
          </Flex>
          <Spacer />
          <Button colorScheme='teal' onClick={logoutHandler}>
            Log Out
          </Button>
        </>
      ) : (
        <ButtonGroup gap='2'>
          <Button colorScheme='teal'>
            <Link to='/signup'>Sign Up</Link>
          </Button>
          <Button colorScheme='teal'>
            <Link to='/signin'>Log In</Link>
          </Button>
        </ButtonGroup>
      )}
    </Flex>
  )
}

export default Header
