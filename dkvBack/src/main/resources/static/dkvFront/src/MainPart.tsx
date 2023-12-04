import './styles/MainPart.css'

interface Props {
    isModalOpen: boolean
}

function MainPart({ isModalOpen }: Props) {
    return (
        <>
            <div className='json'>
                <p>json:</p>
                <textarea className='area' style={{background: isModalOpen ? "gray" : "white"}} disabled={true}></textarea>
            </div>
        </>
    )
}

export default MainPart