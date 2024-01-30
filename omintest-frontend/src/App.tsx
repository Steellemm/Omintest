import { BrowserRouter } from 'react-router-dom'
import AppRouter from './router/AppRouter.tsx'
import * as PIXI from 'pixi.js'
import { useState } from 'react'
import { Context } from './main.tsx'
import Overlay from './components/overlay/Overlay.tsx'
import { ITest } from './types/pages/tests-data'


function App() {

    const [isShowPopup, setIsShowPopup] = useState<boolean>(false)

    const [tests, setTests] = useState<ITest[]>([
        {
            id: '1',
            title: 'Тест 1',
            description: 'Описание'
        },
        {
            id: '2',
            title: 'Тест 2',
            description: 'Описание'
        },
        {
            id: '3',
            title: 'Тест 3',
            description: 'Описание'
        }
    ])

    return (
        <Context.Provider value={{ isShowPopup, setIsShowPopup, tests, setTests }}>
            <BrowserRouter>
                <Overlay/>
                <AppRouter/>
            </BrowserRouter>
        </Context.Provider>
    )
}


export default App
