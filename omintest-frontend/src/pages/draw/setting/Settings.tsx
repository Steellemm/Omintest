import { FC } from 'react'
import Dropdown from '../../../components/dropdown/Dropdown.tsx'
import { IItemDropdown } from '../../../types/components/dropdown/dropdown-props-type.ts'
import styles from './Settings.module.scss'
import SwitchGroup from '../../../components/switch-group/SwitchGroup.tsx'
import Accordion from '../../../components/accordion/Accordion.tsx'
import Table from '../../../components/table/Table.tsx'
import Textarea from '../../../components/textarea/Textarea.tsx'

const Settings: FC = () => {

    const itemsDropdown: IItemDropdown [] = [
        { id: '1', title: 'Параметр 1' },
        { id: '2', title: 'Параметр 2' },
        { id: '3', title: 'Параметр 3' },
        { id: '4', title: 'Параметр 4' },
        { id: '5', title: 'Параметр 5' },
        { id: '6', title: 'Параметр 6' },
        { id: '7', title: 'Параметр 7' }
    ]

    return (
        <div className={styles.settings}>
            <div className={styles.title}>Настройки</div>
            <div className={styles.inner}>
                <Textarea/>
                <SwitchGroup/>
                <Dropdown items={itemsDropdown}/>
                <Accordion>
                    <Table/>
                </Accordion>
            </div>
        </div>
    )
}

export default Settings
