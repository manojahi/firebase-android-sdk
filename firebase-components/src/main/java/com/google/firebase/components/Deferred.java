// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/**
 * Mutable thread-safe {@link Provider}.
 *
 * <p>Can be updated to a new value with the {@link #set(Provider)} method.
 *
 * <p>The intent of this class is to be used in place of missing {@link Component} dependencies so
 * that they can be updated if dependencies are loaded later.
 */
class Deferred<T> implements Provider<T> {
  private static final Provider<Object> EMPTY_PROVIDER = () -> null;

  @SuppressWarnings("unchecked")
  private volatile Provider<T> delegate = (Provider<T>) EMPTY_PROVIDER;

  @Override
  public T get() {
    return delegate.get();
  }

  void set(Provider<T> newProvider) {
    delegate = newProvider;
  }
}
