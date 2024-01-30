import { FC } from 'react'
import svg from '../../../assets/svg/sprite.svg'
import { IButtonArrowPropsType } from '../../../types/components/buttons/button-arrow-props-type.ts'
import styles from './ButtonArrow.module.scss'
import cn from 'classnames'

const ButtonArrow: FC<IButtonArrowPropsType> = ({ text, setIsActive, isActive }) => {
    return (
        <button className={styles.btn} onClick={() => setIsActive(!isActive)}>
            <div className={styles.text}>{text}</div>
            <svg className={cn(styles.icon, isActive && styles.iconActive)}>
                <use href={`${svg}#arrow`}></use>
            </svg>
        </button>
    )
}

export default ButtonArrow
