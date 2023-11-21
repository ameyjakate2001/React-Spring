import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { Box, Flex } from '@chakra-ui/react'
import { Heading, Button } from '@chakra-ui/react'
import { Input } from '@chakra-ui/react'
import { useNavigate } from 'react-router-dom'
import { Spinner } from '@chakra-ui/react'

import { FormControl, FormLabel } from '@chakra-ui/react'
import img from '../bg-2.jpg'
import { useContext } from 'react'
import { UserContext } from '../context/UserContext'

const RegisterScreen = () => {
  const navigate = useNavigate()
  const { userData, setUserData } = useContext(UserContext)
  useEffect(() => {
    if (userData) navigate('/pokemons')
  }, [navigate, userData])
  const [username, setUsername] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState(null)
  const [loading, setLoading] = useState(false)

  const signUpHandler = () => {
    if (!username || !email || !password) {
      setLoading(true)
      setError('Please fill all fields')
      return
    }
    setLoading(true)
    var data = JSON.stringify({
      name: username,
      username: email,
      password,
    })

    var config = {
      method: 'post',
      url: 'http://localhost:9000/api/auth/register',
      headers: {
        'Content-Type': 'application/json',
      },
      data: data,
    }

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data))
        setLoading(false)
        alert('Now you can log in')
        setError(null)
        navigate('/signin')
      })
      .catch(function (error) {
        console.log(error)
        setError(error.response ? error.response.data : 'Error Happened')
        setLoading(false)
      })
  }

  return (
    <Flex gap='2'>
      <Box mt='4' flex='1.5'>
        <img src={img} />
      </Box>
      <Box
        p='5'
        mt='4'
        borderWidth='1px'
        borderRadius='lg'
        borderColor='gray.300'
        flex='1.5'
      >
        <Heading>Sign Up</Heading>
        <Box m='25px'>
          <FormControl isRequired>
            <FormLabel>Username</FormLabel>
            <Input
              type='text'
              placeholder='Username'
              onChange={(e) => setUsername(e.target.value)}
            />
          </FormControl>
        </Box>
        <Box m='25px'>
          <FormControl isRequired>
            <FormLabel>Email Id</FormLabel>
            <Input
              type='email'
              placeholder='Email'
              onChange={(e) => setEmail(e.target.value)}
            />
          </FormControl>
        </Box>
        <Box m='25px'>
          <FormControl isRequired>
            <FormLabel>Password</FormLabel>
            <Input
              type='password'
              placeholder='Password'
              onChange={(e) => setPassword(e.target.value)}
            />
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
        <Button colorScheme='teal' ml='25px' onClick={signUpHandler}>
          {loading ? <Spinner /> : ' Sign Up'}
        </Button>
      </Box>
    </Flex>
  )
}

export default RegisterScreen
