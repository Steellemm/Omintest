import React, { createContext } from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './input.css'
import { IContextType } from './types/context-type.ts'


export const Context = createContext<IContextType>({
    isShowPopup: false,
    setIsShowPopup: (param) => param,
    tests: [],
    setTests: (param) => param
})

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <App/>
    </React.StrictMode>,
)
