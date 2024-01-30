import { FC, useContext } from 'react'
import styles from './Overlay.module.scss'
import { Context } from '../../main.tsx'
import cn from 'classnames'

const Overlay: FC = () => {
    const { isShowPopup, setIsShowPopup } = useContext(Context)

    const handlerClick = () => {
        if (isShowPopup) {
            setIsShowPopup(false)
        }
    }

    return (
        <div className={cn(styles.overlay, isShowPopup && styles.showPopup)} onClick={handlerClick}></div>
    )
}

export default Overlay
