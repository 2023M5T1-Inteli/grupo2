import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import App from '../App'
import CriarProjeto from '../pages/Projects'
import Graph from '../pages/Graph'
import Login from '../pages/Login'
import Map from '../pages/Map'

const Router = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={< Login />} />
                <Route path="/projects" element={< CriarProjeto />} />
                <Route path="/map" element={< Map/>} />
                <Route path="/graph" element={< Graph/>} />
            </Routes>
        </BrowserRouter>
    )
}

export default Router;