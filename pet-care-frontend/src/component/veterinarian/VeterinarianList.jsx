import React, { useEffect } from 'react'

const VeterinarianList = () => {
  const [veterinarians, setVeterinarians] = useState([]);
  const [errors, setErrors] = useState("");

  useEffect(()=>{
    getVeterinarians().then(data=>setVeterinarians(data)).catch((error)=>setErrors(error.message))
  },[])

  if (veterinarians.length === 0) {
    return <p>Veterinarian is empty</p>
  }

  return (
    <div>VeterinarianList</div>
  )
}

export default VeterinarianList