import { FC, PropsWithChildren, useState } from 'react'
import styles from './Accordion.module.scss'
import ButtonArrow from '../buttons/button-arrow/ButtonArrow.tsx'
import cn from 'classnames'

const Accordion: FC<PropsWithChildren> = ({ children }) => {

    const [isActive, setIsActive] = useState<boolean>(false)

    return (
        <div className={styles.accordion}>
            <ButtonArrow text={'Табличное поле'} isActive={isActive} setIsActive={setIsActive}/>

            <div className={cn(styles.wrapper, isActive && styles.wrapperActive)}>
                {children}
            </div>
        </div>
    )
}

export default Accordion
