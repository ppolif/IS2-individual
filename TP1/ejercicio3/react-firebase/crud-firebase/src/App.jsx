import { useState } from 'react'

import './App.css'

import { Show } from './components/Show'
import { Edit } from './components/Edit'
import { Create } from './components/Create'

import { BrowserRouter, Route, Routes } from 'react-router-dom'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
        <Routes>
            <Route path='/' element={<Show/>}></Route>
            <Route path='/create' element={<Create/>}></Route>
            <Route path='/edit/:id' element={<Edit/>}></Route>
        </Routes>
      </BrowserRouter>

      
    </>
  )
}

export default App
