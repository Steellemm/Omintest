import { FC } from 'react'
import styles from './Actions.module.scss'
import svg from '../../../assets/svg/sprite.svg'
import cn from 'classnames'

const Actions: FC = () => {
    return (
        <div className={styles.actions}>
            <button className={styles.btn}>
                <svg className={cn(styles.icon, styles.iconPlay)}>
                    <use href={`${svg}#play`}/>
                </svg>
                <span className={styles.text}>Запустить</span>
            </button>
            <button className={styles.btn}>
                <svg className={styles.icon}>
                    <use href={`${svg}#save`}/>
                </svg>
                <span className={styles.text}>Сохранить</span>
            </button>
            <button className={styles.btn}>
                <svg className={styles.icon}>
                    <use href={`${svg}#download`}/>
                </svg>
                <span className={styles.text}>Скачать</span>
            </button>
        </div>
    )
}

export default Actions
