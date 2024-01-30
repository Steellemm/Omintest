import { FC } from 'react'
import styles from './Button.module.scss'
import { IButtonPropsType } from '../../../types/components/buttons/button-props-type.ts'
import cn from 'classnames'
import { Link } from 'react-router-dom'
import { LOGIN_ROUTE, REGISTRATION_ROUTE } from '../../../utils/variables.ts'

const Button: FC<IButtonPropsType> = ({ type, text, filled, disabled, submit }) => {


    return (
        <button type={submit ? 'submit' : 'button'}
            className={cn(styles.btn, filled && styles.filled, disabled && styles.disabled, type !== 'over' && styles.isLink)}
        >
            {type !== 'over' ?
                <Link to={type === 'signup' ? LOGIN_ROUTE : REGISTRATION_ROUTE} className='w-full h-full p-5'>
                    {text}
                </Link>
                : text}
        </button>
    )
}

export default Button
