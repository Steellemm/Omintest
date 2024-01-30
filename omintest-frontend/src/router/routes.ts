import { IRoute } from '../types/router/router-types.ts'
import { CONFIRM_ROUTE, DRAW_ROUTE, HOME_ROUTE, LOGIN_ROUTE, REGISTRATION_ROUTE, UI_ROUTE } from '../utils/variables.ts'
import { AuthPage, ConfirmPage, DrawPage, HomePage, UiPage } from '../pages'


export const publicRoutes: IRoute[] = [
    {
        path: LOGIN_ROUTE,
        Page: AuthPage
    },
    {
        path: REGISTRATION_ROUTE,
        Page: AuthPage
    },
    {
        path: HOME_ROUTE,
        Page: HomePage
    },
    {
        path: UI_ROUTE,
        Page: UiPage
    },
    {
        path: CONFIRM_ROUTE,
        Page: ConfirmPage
    }
]

export const customRoutes: IRoute[] = [
    {
        path: `${DRAW_ROUTE}/:id`,
        Page: DrawPage
    }
]
