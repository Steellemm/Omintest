import { FC } from 'react'
import { IButtonTextPropsType } from '../../../types/components/buttons/button-text-props-type.ts'
import styles from './ButtonText.module.scss'
import cn from 'classnames'

const ButtonText: FC<IButtonTextPropsType> = ({ primary, text, onClick }) => {
    return (
        <button type="button" className={cn(styles.button, primary && styles.primary)} onClick={() => onClick()}>{text}</button>
    )
}

export default ButtonText
