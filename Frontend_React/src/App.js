import { ChakraProvider } from '@chakra-ui/react'
import { Navigate, Route, Routes, useNavigate } from 'react-router-dom'
import Header from './Components/Header'
import AddPokemonScreen from './screens/AddPokemonScreen'
import useCheckUser from './useCheckUser'
import GetPokemonScreen from './screens/GetPokemonScreen'
import LoginScreen from './screens/LoginScreen'
import RegisterScreen from './screens/RegisterScreen'
import { Container } from '@chakra-ui/react'
import Review from './Components/Review'
import { Spinner } from '@chakra-ui/react'
import { useEffect, useState } from 'react'
import { UserContext } from './context/UserContext'
import PrivateRoute from './Components/PrivateRoute'

function App() {
  const navigate = useNavigate()
  const [userData, setUserData] = useState(null)
  const { loading, data, error } = useCheckUser()

  useEffect(() => {
    if (data) {
      setUserData(data)
    } else if (error) {
      // navigate('/signin') // Use navigate to navigate to the login page
    }
  }, [data, error, navigate])

  if (loading) {
    return (
      <Spinner
        thickness='4px'
        speed='0.65s'
        emptyColor='gray.200'
        color='blue.500'
        size='xl'
      />
    )
  }
  return (
    <ChakraProvider>
      <UserContext.Provider value={{ userData, setUserData }}>
        <div className='App'>
          <Header />
          <Container maxW='container.md'>
            <Routes>
              <Route
                path='/add'
                element={
                  <PrivateRoute>
                    <AddPokemonScreen />
                  </PrivateRoute>
                }
              />
              <Route
                path='/pokemons'
                element={
                  <PrivateRoute>
                    <GetPokemonScreen />
                  </PrivateRoute>
                }
              />
              <Route
                path='/pokemons/reviews/:id'
                exact
                element={
                  <PrivateRoute>
                    <Review />
                  </PrivateRoute>
                }
              />
              <Route path='/signin' element={<LoginScreen />} />
              <Route path='/signup' element={<RegisterScreen />} />
              <Route path='*' element={<Navigate to='/pokemons' />} />
            </Routes>
          </Container>
        </div>
      </UserContext.Provider>
    </ChakraProvider>
  )
}

export default App
