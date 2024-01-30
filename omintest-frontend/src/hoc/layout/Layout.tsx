import Header from '../../components/header/Header.tsx'
import { Outlet, useLocation } from 'react-router-dom'
import { FC } from 'react'
import styles from './Layout.module.scss'
import Wrapper from '../wrapper/Wrapper.tsx'
import Popup from '../../components/popup/Popup.tsx'
import { DRAW_ROUTE } from '../../utils/variables.ts'


const Layout: FC = () => {
    const location = useLocation()
    const isDrawPage = location.pathname === DRAW_ROUTE

    return (
        <div className={styles.layout}>
            <Wrapper>
                <Popup/>
                {!isDrawPage && <Header/>}
                <Outlet/>
            </Wrapper>
        </div>
    )
}

export default Layout
