import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { useContext } from 'react'
import { UserContext } from './context/UserContext'
const useCheckUser = () => {
  //   const navigate = useNavigate()
  const [loading, setLoading] = useState(true)
  const [data, setData] = useState(null)
  const [error, setError] = useState(null)

  useEffect(() => {
    const fetchData = () => {
      var config = {
        method: 'get',
        url: 'http://localhost:9000/api/auth/user',
        headers: {
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      }
      axios(config)
        .then(function (response) {
          console.log(response)
          setData(response.data)
          setLoading(false)
          setError(null)
        })
        .catch(function (error) {
          console.log(error)
          setError(
            error.response ? error.response.data.error : 'Error Happened'
          )
          setLoading(false)
        })
    }

    fetchData()
  }, [])
  return { loading, data, error }
}

export default useCheckUser
