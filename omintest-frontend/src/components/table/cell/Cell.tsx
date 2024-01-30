import { FC, FormEvent, useEffect, useRef, useState } from 'react'
import styles from './Cell.module.scss'
import { TableCellPropsType } from '../../../types/components/table/table-cell-props-type.ts'
import cn from 'classnames'


const Cell: FC<TableCellPropsType> = ({ text }) => {

    const [activeInput, setActiveInput] = useState<boolean>(false)
    const [textValue, setTextValue] = useState<string>('')
    const ref = useRef<HTMLInputElement>(null)

    const handlerClick = () => {
        setActiveInput(true)
        ref.current?.focus()
    }

    const handlerFocus = () => {
        setActiveInput(false)
    }

    const handlerInput = (e: FormEvent<HTMLInputElement>) => {
        setTextValue(e.currentTarget.value)
    }

    useEffect(() => {
        setTextValue(text)
    }, [])


    return (
        <div className={styles.cell}>
            <button className={styles.data} onClick={handlerClick}>{textValue}</button>
            <input
                ref={ref}
                autoFocus={false}
                type="text"
                className={cn(styles.input, activeInput && styles.inputActive)}
                placeholder="Пусто"
                onBlur={handlerFocus}
                value={textValue}
                onChange={handlerInput}
            />
        </div>
    )
}
export default Cell


