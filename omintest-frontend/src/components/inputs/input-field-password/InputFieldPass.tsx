import { FC, FormEvent, useRef, useState } from 'react'
import styles from './InputFieldPass.module.scss'
import { InputPropsType } from '../../../types/components/inputs/input-props-type.ts'
import svg from '../../../assets/svg/sprite.svg'
import HelperText from '../../helper-text/HelperText.tsx'
import cn from 'classnames'

const InputFieldPass: FC<InputPropsType> = ({ onChange, title, placeholder = '********', checked, active, error }) => {
    const refInput = useRef<HTMLInputElement>(null)
    const [inputValue, setInputValue] = useState<string>('')
    const [isShowPassword, setIsShowPassword] = useState<boolean>(false)


    const handlerShowPassword = () => {
        refInput.current?.setAttribute('type', isShowPassword ? 'text' : 'password')
        setIsShowPassword(!isShowPassword)
    }

    const handlerInput = (e: FormEvent<HTMLInputElement>) => {
        setInputValue(e.currentTarget.value)
        onChange(e.currentTarget.value)
    }

    return (
        <div className={styles.field}>
            <div className={cn(styles.title, active && styles.titleActive, error && styles.titleError)}>{title}</div>
            <div className={styles.inputWrapper}>
                <input type="password"
                    ref={refInput}
                    className={cn(
                        styles.input,
                        active && styles.inputActive,
                        checked && styles.inputChecked,
                        error && styles.inputError
                    )}
                    placeholder={placeholder}
                    onChange={handlerInput}
                    value={inputValue}
                />
                <button className={styles.btn} onClick={handlerShowPassword}>
                    <svg className={cn(styles.icon, active && styles.iconActive, error && styles.iconError)}>
                        <use href={`${svg}#eye`}></use>
                    </svg>
                </button>
            </div>
            {error && <HelperText error={!!error.message} text={error.message}/>}
        </div>
    )
}

export default InputFieldPass
