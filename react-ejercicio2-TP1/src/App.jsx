import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import { Tarjeta } from './components/Tarjeta'
import { ELEMENTOS } from './data/elements'

function App() {
  {/*
  const [count, setCount] = useState(0)
  */}

  const elementoActual = ELEMENTOS[4]

  return (
    <>
      <Tarjeta item={elementoActual}/>
    </>
  )
}

export default App
