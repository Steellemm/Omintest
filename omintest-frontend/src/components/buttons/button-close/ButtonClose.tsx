import { FC } from 'react'
import styles from './ButtonClose.module.scss'
import svg from '../../../assets/svg/sprite.svg'
import { IButtonClosePropsType } from '../../../types/components/buttons/button-close-props-type.ts'

const ButtonClose: FC<IButtonClosePropsType> = ({ handlerRemove, id }) => {
    return (
        <button className={styles.btn} onClick={() => handlerRemove(id)}>
            <svg className={styles.icon}>
                <use href={`${svg}#close`}></use>
            </svg>
        </button>
    )
}

export default ButtonClose
