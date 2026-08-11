import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import { Tarjeta } from './components/Tarjeta'
import { ELEMENTOS } from './data/elements'

function App() {
  const [indice, setIndice] = useState(0)
  
  //Podría solo hacer un useState con appeals pero como quiero en algun momento
  //agregar que se muestren al final todos los appeals y un "que dice sobre vos"
  //o quiza temas relacionados para investigar voy a usar una lista de appeals

  const [likes, setLikes] = useState([])

  const elementoActual = ELEMENTOS[indice]

  const esAppealing = likes.includes(elementoActual.id)

  //funciones para manejar clicks
  const clickAppeals = () => {
    setLikes((prevLikes) =>
      esAppealing
        ? prevLikes.filter((id) => id !== elementoActual.id)
        : [...prevLikes, elementoActual.id]
    );
  }

  const clickNext = () => {
    setIndice((prev) => (prev+1) % ELEMENTOS.length)
  }

  return (
    <>
      <Tarjeta 
        item={elementoActual} 
        appeals={esAppealing} 
        manejoAppeals={clickAppeals}
        manejoNext={clickNext}
      />
    </>
  )
}

export default App
