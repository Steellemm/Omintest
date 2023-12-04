import { useEffect, useState } from "react";
import { createPortal } from "react-dom";

interface PortalProps {
    id: string,
    children: React.ReactNode
}

interface containerOptions {
    id: string,
    mountNode?: HTMLElement
}

function Portal(props: PortalProps) {
    const { id, children } = props
    const [container, setContainer] = useState<HTMLElement>()

    useEffect(() => {
        if (id) {
            const portalContainer = document.getElementById(id)

            if (!portalContainer) {
                throw new Error("Container error")
            }

            setContainer(portalContainer)
        }
    }, [id])

    return container ? createPortal(children, container) : null
}

function createContainer(options: containerOptions) {
    if (document.getElementById(options.id)) {
        return
    }

    const { id, mountNode = document.body } = options

    const portalContainer = document.createElement('div')
    portalContainer.setAttribute('id', id)
    mountNode.appendChild(portalContainer)
}

export { createContainer }
export default Portal