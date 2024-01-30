import { FC } from 'react'
import styles from './ButtonAddTest.module.scss'
import svg from './../../../assets/svg/sprite.svg'
import { IButtonAddTestPropsType } from '../../../types/components/buttons/button-add-test-props-type.ts'

const ButtonAddTest: FC<IButtonAddTestPropsType> = ({ onClick }) => {
    return (
        <button className={styles.card} onClick={onClick}>
            <svg className={styles.icon}>
                <use href={`${svg}#plus`}/>
            </svg>
            <span className={styles.text}>Создать тест</span>
        </button>
    )
}

export default ButtonAddTest
