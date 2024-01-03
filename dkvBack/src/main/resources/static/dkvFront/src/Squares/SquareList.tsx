import {useState} from 'react'

import '../styles/SquareList.css'

type SquareListProps = {
    squares: Square[],
    onOpenSquare: (square: Square) => void,
}

function SquareList({squares, onOpenSquare}: SquareListProps) {
    const [selectedSquare, setSelectedSquare] = useState<Square>()
    const squaresNeed = squares.map((item) => (
            <div className='sq' key={item.id} onDoubleClick={() => onOpenSquare(item)}
                 onClick={() => setSelectedSquare(item)}
                 style={{borderColor: selectedSquare == item ? "yellow" : "black"}}>
                <p className='p'>{item.name}</p>
            </div>
        ))

    return (
        <>
            {squaresNeed}
        </>
    )
}

export default SquareList