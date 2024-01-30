import { FC } from 'react'
import styles from './ButtonAdd.module.scss'
import svg from '../../../assets/svg/sprite.svg'
import cn from 'classnames'
import { IButtonAddPropsType } from '../../../types/components/buttons/button-add-props-type.ts'

const ButtonAdd: FC<IButtonAddPropsType> = ({ handlerAdd }) => {
    return (
        <button className={cn(styles.btn)} onClick={() => handlerAdd()}>
            <svg className={styles.icon}>
                <use href={`${svg}#plus`}></use>
            </svg>
            <span className={styles.text}>Добавить строку</span>
        </button>
    )
}

export default ButtonAdd
