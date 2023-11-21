import React from 'react'
import { useContext } from 'react'
import { UserContext } from '../context/UserContext'
import { Navigate } from 'react-router-dom'

const PrivateRoute = ({ children }) => {
  const { userData } = useContext(UserContext)

  if (userData) {
    return children
  }

  return <Navigate to='/signup' />
}

export default PrivateRoute
