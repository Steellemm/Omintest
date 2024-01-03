import {useState} from 'react'
import axios from "axios";
import './styles/App.css'
import SquarePart from './Squares/SquarePart'
import MainPart from "./MainPart.tsx";
import Modal from "./Modal.tsx";

async function fetchSquares() {
    const response = await axios.get<Square[]>("http://localhost:8080/squares")
    return response
}

function App() {
    const [squares, setSquares] = useState<Square[]>([])
    const [isModalActive, setModalActive] = useState(false)
    const [squareName, setSquareName] = useState("")

    const handleModalOpen = (square: Square) => {
        setSquareName(square.name)
        setModalActive(true)
    }
    const handleModalClose = () => {
        setSquareName("")
        setModalActive(false)
    }

    if (squares.length == 0) {
        fetchSquares().then(it => {
            setSquares(it.data)
        })
    }

    return (
        <>
            <div className='app' style={{background: isModalActive ? "gray" : "white"}}>
                <div>
                    {isModalActive && (
                        <Modal title={squareName} onClose={handleModalClose} />
                    )}
                </div>
                <SquarePart squares={squares} onOpenSquare={handleModalOpen}/>
                <MainPart isModalOpen={isModalActive}/>
            </div>
        </>
    )
}

export default App
