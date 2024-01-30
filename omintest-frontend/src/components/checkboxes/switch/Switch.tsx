import { FC } from 'react'
import styles from './Switch.module.scss'
import { ISwitchPropsType } from '../../../types/components/checkboxes/switch-props-type.ts'

const Switch: FC<ISwitchPropsType> = ({ text }) => {
    return (
        <label className={styles.wrapper}>
            <span className={styles.title}>{text}</span>
            <label className={styles.switch}>
                <input type="checkbox"/>
                <span className={styles.slider}></span>
            </label>
        </label>
    )
}

export default Switch
