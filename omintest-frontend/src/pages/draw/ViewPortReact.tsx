import React, {forwardRef, useEffect} from "react";
import * as PIXI from "pixi.js";
import {PixiComponent, useApp} from "@pixi/react";
import {Viewport as PixiViewport} from "pixi-viewport";
import {EventSystem} from "@pixi/events";
import {Container as PixiContainer} from "@pixi/display";

export interface ViewportProps {
    width: number;
    height: number;
    outer: boolean;
    children?: React.ReactNode;
}

export interface PixiComponentViewportProps extends ViewportProps {
    app: PIXI.Application;
}

const PixiComponentViewport = PixiComponent("Viewport", {
    create: (props: PixiComponentViewportProps) => {

        const events = new EventSystem(props.app.renderer);
        events.domElement = props.app.renderer.view as any;

        const viewport = new PixiViewport({
            screenWidth: props.app.screen.width,
            screenHeight: props.app.screen.height,
            worldWidth: props.width,
            worldHeight: props.height,
            ticker: props.app.ticker,
            events: events,
        });
        if (!props.outer) {
            viewport
            .drag()
            .pinch()
            //.decelerate()
            .wheel()
            .clampZoom({
                minWidth: 1,
                minHeight: 1,
                maxWidth: 10000,
                maxHeight: 10000,
            });
        }
        
//         useEffect(() => {
//         const points = [];
//         const pointsDensity = 0.01; // Adjust the density of points to cover the entire page
//         if (props.outer) {
//     for (let x = 0; x < window.innerWidth; x += 10) {
//       for (let y = 0; y < window.innerHeight; y += 10) {
//         const point = new PIXI.Graphics();
//         point.beginFill(0xcccccc, 1);
//         point.drawCircle(0, 0, 1.5);
//         point.endFill();
//         point.position.set(x, y);
//         points.push(point);
//       }
//     }
//     // Add the points to the viewport
//     points.forEach((point) => viewport.addChild(point));
//         return () => {alert('changed')}
// }
//         }, [viewport]);

    
        // const points = [];
        // const numPoints = 100;
        // for (let i = 0; i < numPoints; i++) {
        //   const point = new PIXI.Graphics();
        //   point.beginFill(0xFFFFFF);
        //   point.drawCircle(Math.random() * window.innerWidth, Math.random() * window.innerHeight, 1);
        //   point.endFill();
        //   points.push(point);
        //   props.app.stage.addChild(point);
        // }
        const points = [];
        const pointsDensity = 0.01; // Adjust the density of points to cover the entire page
        if (props.outer) {
    for (let x = 0; x < window.innerWidth; x += 10) {
      for (let y = 0; y < window.innerHeight; y += 10) {
        const point = new PIXI.Graphics();
        point.beginFill(0xcccccc, 1);
        point.drawCircle(0, 0, 1.5);
        point.endFill();
        point.position.set(x, y);
        points.push(point);
      }
    }
}

    // Add the points to the viewport
    points.forEach((point) => viewport.addChild(point));

        return viewport;
    },

    willUnmount: (instance: PixiViewport, parent: PixiContainer) => {
        instance.options.noTicker = true;
        instance.destroy({children: true, texture: true, baseTexture: true})
    }
});

const ViewportReact = forwardRef(
    (props: ViewportProps, ref: React.Ref<PixiViewport>) => {
        const app = useApp();
        return <PixiComponentViewport ref={ref} app={app} {...props} />;
    }
);

export default ViewportReact;