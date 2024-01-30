import { FC, useState } from 'react'
import styles from './Dropdown.module.scss'
import DropdownPopup from './dropdown-popup/DropdownPopup.tsx'
import svg from '../../assets/svg/sprite.svg'
import cn from 'classnames'
import HelperText from '../helper-text/HelperText.tsx'
import { IDropdownPropsType } from '../../types/components/dropdown/dropdown-props-type.ts'

const Dropdown: FC<IDropdownPropsType> = ({ error, items }) => {

    const [active, setActive] = useState<boolean>(false)
    const [inputValue, setInputValue] = useState<string>('')

    const handleBtn = () => {
        setActive(!active)
    }

    return (
        <div className={cn(styles.dropdown, active && styles.dropdownActive)}>
            <div className={styles.title}>Some Title</div>

            <div className={styles.inner}>
                <button className={styles.toggle} onClick={handleBtn}>
                    <input
                        tabIndex={-1}
                        type="text"
                        className={styles.input}
                        placeholder="Не выбрано"
                        readOnly
                        value={inputValue}
                    />
                    <svg aria-hidden="true" tabIndex={-1} className={styles.icon}>
                        <use href={`${svg}#arrow`}/>
                    </svg>
                </button>
            </div>
            <DropdownPopup status={active} error={error} setInputValue={setInputValue} setActive={setActive} items={items}/>
            {error && <HelperText error={error} text={'Вспомагательный текст'}/>}
        </div>
    )
}

export default Dropdown
