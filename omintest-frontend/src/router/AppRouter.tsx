import { FC } from 'react'
import { Navigate, Route, Routes } from 'react-router-dom'
import { IRoute } from '../types/router/router-types.ts'
import { customRoutes, publicRoutes } from './routes.ts'
import Layout from '../hoc/layout/Layout.tsx'


const AppRouter: FC = () => {
    return (
        <Routes>
            <Route path="/" element={<Layout/>}>
                {publicRoutes.map(({ path, Page }: IRoute) =>
                    <Route key={path} path={path} element={<Page/>}/>
                )}
                <Route path="*" element={<Navigate to="/"/>}></Route>
            </Route>
            {customRoutes.map(({ path, Page }: IRoute) =>
                <Route key={path} path={path} element={<Page/>}/>
            )}
        </Routes>
    )
}

export default AppRouter
