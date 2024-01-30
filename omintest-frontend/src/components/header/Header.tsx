import styles from './Header.module.scss'
import Logo from '../logo/Logo.tsx'
import ButtonProfile from '../buttons/button-profile/ButtonProfile.tsx'
import { useLocation } from 'react-router-dom'
import { LOGIN_ROUTE } from '../../utils/variables.ts'
import Button from '../buttons/button/Button.tsx'

const Header = () => {
    const location = useLocation()
    const isLogin = location.pathname === LOGIN_ROUTE


    const isAuth = false

    return (
        <header className={styles.header}>
            <div className={styles.container}>
                <Logo/>
                <div>
                    {isAuth ? <ButtonProfile/> : <Button type={isLogin ? 'signin' : 'signup'} text={isLogin ? 'Регистрация' : 'Войти'}/>}
                </div>
            </div>
        </header>
    )
}

export default Header
