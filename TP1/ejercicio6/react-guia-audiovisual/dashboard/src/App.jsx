import { useState } from 'react'
import './App.css'
import Acceso from './components/Acceso'
import Menu from './components/Menu'
import Contenido from './components/Contenido'

function App() {

  return (
    <div className="dashboard">
        <Acceso></Acceso>
        <Menu></Menu>
    </div>
  )
}

export default App
