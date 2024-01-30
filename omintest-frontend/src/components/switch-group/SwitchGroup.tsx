import { FC } from 'react'
import HelperText from '../helper-text/HelperText.tsx'
import styles from './SwitchGroup.module.scss'
import Switch from '../checkboxes/switch/Switch.tsx'
import { ISwitchGroupPropsType } from '../../types/components/checkboxes/switch-group-props-type.ts'

const SwitchGroup: FC<ISwitchGroupPropsType> = ({ isError }) => {
    return (
        <div className={styles.switch}>
            <Switch text={'Параметр 1'}/>
            {isError && <HelperText error={isError} text={'Вспомогательный текст'}/>}
        </div>
    )
}

export default SwitchGroup
