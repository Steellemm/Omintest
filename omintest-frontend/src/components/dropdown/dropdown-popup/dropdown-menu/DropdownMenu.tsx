import { FC, MouseEvent } from 'react'
import styles from './DropdownMenu.module.scss'
import { IDropdownMenuPropsType } from '../../../../types/components/dropdown/dropdown-menu-props-type.ts'

const DropdownMenu: FC<IDropdownMenuPropsType> = ({ setActive, setInputValue, items }) => {

    const handlerItem = (e: MouseEvent<HTMLButtonElement>) => {
        setInputValue(e.currentTarget.innerText)
        setActive(false)
    }

    return (
        <div className={styles.menu}>
            {items?.map(item =>
                <button key={item.id} className={styles.item} onClick={(e) => handlerItem(e)}>{item.title}</button>
            )}
        </div>
    )
}

export default DropdownMenu
