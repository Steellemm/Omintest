import { ChangeEvent, FC } from 'react'
import styles from './Checkbox.module.scss'
import { ICheckboxPropsType } from '../../../types/components/checkboxes/checkbox-props-type.ts'
import cn from 'classnames'

const Checkbox: FC<ICheckboxPropsType> = ({ onChange, text, rounded }) => {

    const handlerChange = (e: ChangeEvent<HTMLInputElement>) => {
        onChange(e.target.checked)
    }

    return (
        <div className={styles.checkbox}>
            <label className={styles.label}>
                <input
                    className={cn(styles.input, rounded && styles.inputRounded)}
                    type="checkbox"
                    onChange={(e) => handlerChange(e)}/>
                {text && <span className={styles.text}>{text}</span>}
            </label>
        </div>
    )
}

export default Checkbox
