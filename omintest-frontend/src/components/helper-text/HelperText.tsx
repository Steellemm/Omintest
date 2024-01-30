import { FC } from 'react'
import { HelperTextPropsType } from '../../types/components/helper-text/helper-text-props-type.ts'
import styles from './HelperText.module.scss'
import cn from 'classnames'

const HelperText: FC<HelperTextPropsType> = ({ error, text }) => {
    return (
        <div className={cn(styles.text, error && styles.textError)}>
            {text}
        </div>
    )
}

export default HelperText
