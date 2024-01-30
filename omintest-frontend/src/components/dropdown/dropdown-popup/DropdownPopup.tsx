import { FC } from 'react'
import styles from './DropdownPopup.module.scss'
import Search from '../../search/Search.tsx'
import cn from 'classnames'
import { IDropdownPopupPropsType } from '../../../types/components/dropdown/dropdown-popup-props-type.ts'
import DropdownMenu from './dropdown-menu/DropdownMenu.tsx'

const DropdownPopup: FC<IDropdownPopupPropsType> = ({ status, error, setInputValue, setActive, items }) => {


    return (
        <div className={cn(styles.popup, status && styles.popupActive, error && styles.popupError)}>
            <div className='mb-10p'>
                <Search small={true}/>
            </div>
            <DropdownMenu setInputValue={setInputValue} setActive={setActive} items={items}/>

        </div>
    )
}

export default DropdownPopup
