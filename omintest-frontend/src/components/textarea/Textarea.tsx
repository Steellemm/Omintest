import styles from './Textarea.module.scss'
import { FC } from 'react'

const Textarea: FC = () => {
    return (
        <div className={styles.field}>
            <div className={styles.title}>Текстовое поле<span>*</span></div>
            <textarea
                className={styles.textarea}
                placeholder="Заполните текстовое поле"
            ></textarea>
        </div>
    )
}

export default Textarea
