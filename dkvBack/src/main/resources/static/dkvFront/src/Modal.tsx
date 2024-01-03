import { useEffect, useState, useCallback, useRef } from "react";
import type { MouseEventHandler } from "react";
import Portal, { createContainer } from "./Portal.tsx";

import './styles/Modal.css'

type Props = {
    onClose?: () => void;
    title: string
}

export default function Modal(props: Props) {
    const { onClose} = props
    const [isMounted, setMounted] = useState(false)
    const rootRef = useRef<HTMLDivElement>(null)
    const handleClose: MouseEventHandler<HTMLButtonElement> = useCallback(() => {
        onClose?.();
    }, [onClose])

    useEffect(() => {
        createContainer({ id: 'modal-id' })
        setMounted(true)
    }, [])

    return (
        isMounted ? (
            <Portal id={ 'modal-id' }>
                <div className='wrap' ref={ rootRef }>
                    <div className='content'>
                        <h3>Введите конфигурацию</h3>
                        <button
                            type="button"
                            className='closeButton'
                            onClick={ handleClose }
                        >Close</button>
                    </div>
                </div>
            </Portal>
        ) : null
    )
}