import { ApplicationConfig, mergeApplicationConfig } from '@angular/core';
import { provideServerRendering } from '@angular/platform-server';

import { appConfig } from './app.config';
import { provideHttpClient } from '@angular/common/http';

export const serverConfig: ApplicationConfig = {
  providers: [provideServerRendering(), provideHttpClient()],
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
