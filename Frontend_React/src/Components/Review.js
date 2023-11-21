import React, { useState, useEffect } from 'react'
import { Heading, Flex, Box } from '@chakra-ui/react'
import axios from 'axios'
import { Skeleton, SkeletonCircle, SkeletonText } from '@chakra-ui/react'

import { useParams } from 'react-router-dom'
const Review = () => {
  const [reviews, setReviews] = useState([])
  const [loading, setLoading] = useState(true)
  const { id } = useParams()
  console.log(id)

  useEffect(() => {
    var config = {
      method: 'get',
      url: `http://localhost:9000/api/reviews/get-pockemon/${id}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    }

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data))
        setReviews(response.data)
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
        reviews.map((review) => (
          <Flex alignItems='center' gap='2' wrap='wrap'>
            <Box
              flexGrow='1'
              p='3'
              m='3'
              borderWidth='1px'
              borderRadius='md'
              borderColor='gray.200'
            >
              <Heading as='h6'>{review.description}</Heading>
              <p>Starts: {review.stars}</p>
            </Box>
          </Flex>
        ))
      )}
    </Box>
  )
}

export default Review
