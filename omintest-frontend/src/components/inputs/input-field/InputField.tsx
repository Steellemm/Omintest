import { FC, FormEvent, useState } from 'react'
import styles from './InputField.module.scss'
import { InputPropsType } from '../../../types/components/inputs/input-props-type.ts'
import HelperText from '../../helper-text/HelperText.tsx'
import cn from 'classnames'

const InputField: FC<InputPropsType> = ({ onChange, title, placeholder, error, active, checked }) => {
    const [inputValue, setInputValue] = useState<string>('')

    const handlerInput = (e: FormEvent<HTMLInputElement>) => {
        setInputValue(e.currentTarget.value)
        onChange(e.currentTarget.value)
    }


    return (
        <div className={styles.field}>
            <div className={cn(styles.title, active && styles.titleActive, error && styles.titleError)}>{title}</div>
            <input
                type="text"
                className={cn(styles.input,
                    active && styles.inputActive,
                    checked && styles.inputChecked,
                    error && styles.inputError)}
                placeholder={placeholder}
                onChange={(e) => handlerInput(e)}
                value={inputValue}
            />
            {error && <HelperText text={error.message} error={!!error.message}/>}
        </div>
    )
}
export default InputField
