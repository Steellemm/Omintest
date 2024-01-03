import SquareList from "./SquareList"
import '../styles/SquarePart.css'

interface PartProps {
    squares: Array<Square>,
    onOpenSquare: (square: Square) => void
}

function SquarePart({squares, onOpenSquare} : PartProps) {
    return (
        <>
            <div className="sqrs">
                <SquareList squares={squares} onOpenSquare={onOpenSquare}/>
            </div>
        </>
    )
}

export default SquarePart